import {BrowserRouter as Router, Switch, Route, useHistory} from 'react-router-dom';
import Register from "./pages/Register";
import Login from "./pages/Login";
import Home from "./pages/Home";
import AdminPage from "./Admin/AdminPage";
import MentorPage from "./Mentor/MentorPage";
import ParentPage from "./Parent/ParentPage";
import UserPage from "./User/UserPage";
import Module from "./Admin/Module";
import Lesson from "./Admin/Lesson";
import Task from "./Admin/Task";

function App() {

    return (
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route exact path="/sign-up" component={Register}/>
                <Route exact path="/sign-in" component={Login}/>
                <Route exact path="/admin" component={AdminPage}/>
                <Route exact path="/mentor" component={MentorPage}/>
                <Route exact path="/parent" component={ParentPage}/>
                <Route exact path="/user" component={UserPage}/>
                <Route exact path="/modules" component={Module}/>
                <Route exact path="/lessons" component={Lesson}/>
                <Route exact path="/tasks" component={Task}/>
            </Switch>
        </Router>
    );
}

export default App;