import createView from "../createView.js";

export default function User(props) {
    //language=HTML
    return `

        <div>
            <h1 id="show-username">${props.user.username}</h1>
            <h1 id="show-email">${props.user.email}</h1>
            <h1 id="show-id">${props.user.id}</h1>

            <form>
                <label>Create New Password</label>
                <input type="password" id="update-password-input">
                <button type="button" id="update-password-button">Submit</button>
            </form>


        </div>

    `
}

export function UpdatePasswordEvent() {
    $("#update-password-button").click(function (e) {
        e.preventDefault()
        const id = $("#show-id").text();
        console.log(id);
        let newPass = {
            password: $("#update-password-input").val()
        }

        console.log(newPass);

        let request = {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: newPass.password
        }

        fetch(`http://localhost:8080/api/users/${id}/updatePassword`, request)
            .then(response => {
                console.log(response.status)
                createView("/users")
            });
    })
}


