export default function logOut(props) {
    console.log("you've logged out");

    return `
        <header>
            <h1>Log Out</h1>
        </header>
        <main>
            <div>
                <p>
                    Logged Out
                </p>    
            </div>
        </main>
    `;
}

export function LogOutEvent(){
    window.localStorage.clear()
}