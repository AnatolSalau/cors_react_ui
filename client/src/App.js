import logo from './logo.svg';
import './App.css';

function App() {

      const sendRequest = async (url) => {
            let response = await fetch(url);
            let header = response.headers.get('Access-Control-Allow-Origin');
            console.log('Access-Control-Allow-Origin : ');
            console.log(header);
            let jsonResponse = await response.json();
            return jsonResponse;
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
                                    console.log('XSRF-TOKEN : ');
                                    const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
                                    console.log(csrfToken);
                              }}
                        >
                              Send request
                        </button>
                  </header>
            </div>
      );
}

export default App;
