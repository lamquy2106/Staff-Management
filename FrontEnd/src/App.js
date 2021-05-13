import "./App.css";
import { Switch, Route } from "react-router-dom";
// import Home from "./pages/home";
import Login from "./pages/login";
import DefaultLayout from "./containers/defauLayout";
import User from "./pages/user";

function App() {
  return (
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
    <Switch>
      <Route exact path="/user" name="User page" component={User}></Route>
      <Route exact path="/login" name="Login page" component={Login}></Route>
      <Route exact path="/" name="Login page" component={Login}></Route>
      <Route path="/" name="Home" component={DefaultLayout} />
    </Switch>
  );
}

export default App;
