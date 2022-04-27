import createView from "../createView.js";
import {getHeaders} from "../auth.js";
const BASE_URI = "http://localhost:8080/api/posts";
export default function PostIndex(props) {
    return `
        <header>
        <h1>Posts Page</h1>
    </header>
       <main>
          <div>
             ${props.posts.map(post =>  {
        return` 
                <div>
                    <h1 id="title-${post.id}">${post.title}</h1> 
                    <h3 id="content-${post.id}">${post.content}</h3> 
                    <a href="#" class="edit-post-button" type="button" id="edit-post-${post.id}" data-id="${post.id}">edit</a>   
                    <a href="#" class="delete-post-button" type="button" id="delete-post-${post.id}" data-id="${post.id}">delete</a>
                </div>
             `}).join('')}
          </div>
          <hr>
            <div>
              <form>
                <input disabled  type="text" id="add-post-id" value="0">
                <label>Title</label>
                <input id="add-post-title"> 
                <label>Content</label>
                <input id="add-post-content">
                <button type="button" id="add-post-button">Submit</button>
              </form>
          </div>
       </main>
    `;
}

    export function PostsEvent() {
        createAddPostListener();
        createEditPostListener();
        createDeletePostListeners();

    }

    function createAddPostListener() {
        console.log("adding add post listener");
        $("#add-post-button").click(function (){
            const title = $("#add-post-title").val();
            const content = $("#add-post-content").val();
            const newPost = {
                title,
                content
            }
            console.log("Ready to add " + newPost);

            const request = {
                method: "POST",
                headers: getHeaders(),
                body: JSON.stringify(newPost)
            }

            fetch("http://localhost:8080/api/posts", request)
                .then(res => {
                    console.log(res.status);
                    createView("/posts")
                }).catch(error => {
                    console.log(error);
                    createView("/posts");
            });
        })
    }


    function createEditPostListener() {
        console.log("adding edit post listener");
        $(".edit-post-button").click(function () {
            const id = $(this).data("id");
            const oldTitle = $(`#title-${id}`).html();
            const oldContent = $(`#content-${id}`).text();
            $("#add-post-id").val(id);
            $("#add-post-title").val(oldTitle);
            $("#add-post-content").val(oldContent);

        });
    }

        function createDeletePostListeners(){
            $(".delete-post-button").click(function () {
                const id = $(this).data("id");
                console.log("Delete post with the ID of " + id)

                const request = {
                    method: "DELETE",
                    headers: getHeaders(),
                }

                fetch(`http://localhost:8080/api/posts/${id}`, request)
                    .then(res => {
                        console.log(res.status);
                        createView("/posts")
                    }).catch(error => {
                    console.log(error);
                    createView("/posts");
                });

            });


}