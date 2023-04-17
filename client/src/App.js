import logo from './logo.svg';
import './App.css';
import { useCookies } from 'react-cookie';


function App() {

      const [cookies, setCookie] = useCookies(['user']);
      setCookie('JSESSIONID', '98D8B139BE45354EB455EB9FB9BB3EFC', { path: '/' });
      setCookie('XSRF-TOKEN', 'a4755fd0-a4f1-43f7-b58c-75d85c9e56a1', { path: '/' });
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

      let optionsPost = {
            method: 'POST',
            credentials: 'same-origin',
            redirect: 'follow',
            headers: {
                  Accept: 'application/json',
                  'Content-Type': 'application/json',
                  'Authorization' : 'Basic dXNlcjp1c2Vy',
                  'X-XSRF-TOKEN': 'a4755fd0-a4f1-43f7-b58c-75d85c9e56a1',
                  'Cookie' : 'JSESSIONID=98D8B139BE45354EB455EB9FB9BB3EFC; XSRF-TOKEN=a4755fd0-a4f1-43f7-b58c-75d85c9e56a1',

            }
      }

      const sendRequestBasicAuth = async (url, options) => {
            let response = await fetch(url, options);
            let jsonResponse = await response.json();
            return jsonResponse;
      }

      const sendRequestBasicAuth2 = async (url) => {
            var myHeaders = new Headers();
            myHeaders.append("X-XSRF-TOKEN", "a4755fd0-a4f1-43f7-b58c-75d85c9e56a1");
            myHeaders.append("X-XSRF-TOKEN", "a4755fd0-a4f1-43f7-b58c-75d85c9e56a1");
            myHeaders.append("Authorization", "Basic dXNlcjp1c2Vy");
            myHeaders.append("Cookie", "JSESSIONID=98D8B139BE45354EB455EB9FB9BB3EFC; XSRF-TOKEN=a4755fd0-a4f1-43f7-b58c-75d85c9e56a1");

            var requestOptions = {
                  method: 'POST',
                  headers: myHeaders,
                  redirect: 'follow'
            };

            fetch("https://127.0.0.1:8443/api/v1/users", requestOptions)
                  .then(response => response.text())
                  .then(result => console.log(result))
                  .catch(error => console.log('error', error));
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
                                    sendRequestBasicAuth2('https://127.0.0.1:8443/api/v1/users');
                              }}
                        >
                              Post request User
                        </button>
                  </header>
            </div>
      );
}

export default App;
