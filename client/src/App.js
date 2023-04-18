import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import { useCookies } from "react-cookie";

function App() {
      const [cookies, setCookie] = useCookies(['XSRF-TOKEN'])

      /*setCookie('XSRF-TOKEN', 'bf108123-f914-4abe-b0d4-9206c7e8393f');*/
      setCookie('XSRF-TOKEN', '11111111-f914-4abe-b0d4-9206c7e8393f');
      const sendRequest = async (url) => {
            let response = await fetch(url);
            console.log(response)
            let jsonResponse = await response.json();
            console.log(jsonResponse)
            return jsonResponse;
      }
      let optionsGet = {
            method: 'GET',
            headers: {
                  'Authorization' : 'Basic dXNlcjp1c2Vy',
            },
/*            body: JSON.stringify({
                  'client_id': '(API KEY)',
                  'client_secret': '(API SECRET)',
                  'grant_type': 'client_credentials'
            })*/
      }


      const sendRequestBasicAuth = async (url, options) => {
            let response = await fetch(url, options);
            let jsonResponse = await response.json();
            console.log(jsonResponse);
            return jsonResponse;
      }



      let data = {
            "min":"min"
      };
      const postAxios = (url) => {
            console.log("Cookies:")
            console.log(document.cookie);
            axios.post(url, data, {
                  withCredentials: true,
                  headers: {
                        "Content-Type": "application/json",
                        "Authorization": "Basic dXNlcjp1c2Vy",
                        "X-XSRF-TOKEN": "bf108123-f914-4abe-b0d4-9206c7e8393f",
                        "Access-Control-Allow-Origin": "https://127.0.0.1:8443",
                        'Accept' : 'application/json',
                  }
            }).then(
                  response => console.log(response.data)
            ).catch(
                  error => console.log(error)
            )
      }


      return (
            <div className="App">
                  <header className="App-header">
                        <img src={logo} className="App-logo" alt="logo"/>
                        <p>
                              Edit <code>src/App.js</code> and save to reload.
                        </p>
                        <a
                              className="App-link"
                              href="https://reactjs.org"
                              target="_blank"
                              rel="noopener noreferrer"
                        >
                              Learn React
                        </a>
                        < button
                              onClick={() => {
                                sendRequest('https://127.0.0.1:8443/')
                                      .then((value) => console.log(value));
                              }}
                        >
                              Send request Index
                        </button>
                        < button
                              onClick={() => {
                                    sendRequestBasicAuth('https://127.0.0.1:8443/api/v1/users', optionsGet);
                              }}
                        >
                              Send request User
                        </button>
                        < button
                              onClick={() => {
                                    //sendRequestBasicAuth('https://127.0.0.1:8443/api/v1/users', optionsPost);
                                    postAxios('https://127.0.0.1:8443/api/v1/users');
                              }}
                        >
                              Post request User
                        </button>
                  </header>
            </div>
      );
}

export default App;
