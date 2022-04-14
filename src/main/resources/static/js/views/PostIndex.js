export default function PostIndex(props) {
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div>
                <form>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                  </div>
                  <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                  </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                 </form>
            </div>
            <div>
                ${props.posts.map(post => `<h3>${post.title}</h3>
<h3>${post.content}</h3>
`).join('')}   
            </div>
            
            <div>
                <form>
                <input id="add-post-title">
                    <button type= "button" id="add-post-button" >Submit</button>
                </form>
            </div>
            
        </main>
    `;
}

export function postsEvent() {
    createAddPostListener();
}

function createAddPostListener() {
    console.log("adding add post listener");
    $("add-post-button").click(function (){
        const title = $(("add-post-button")).val();
        // const newPost = {
        //     title,
        //     content
        // }
        console.log("Ready to add " + newPost)
    })
}