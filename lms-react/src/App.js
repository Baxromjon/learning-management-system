import {BrowserRouter as Router, Switch, Route, useHistory} from 'react-router-dom';
import Register from "./pages/Register";
import Login from "./pages/Login";
import Home from "./pages/Home";

function App() {

    return (
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route exact path="/sign-up" component={Register}/>
                <Route exact path="/sign-in" component={Login}/>
            </Switch>
        </Router>
    );
}

export default App;