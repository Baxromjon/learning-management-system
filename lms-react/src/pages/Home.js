import React, {Component} from 'react';

class Home extends Component {
    render() {
        return (
            <div>
                <h1 className="text-center">LMS(Learning Management System) Home page</h1>
                <div style={{marginLeft:"550px", marginRight:"auto"}}>
                    <button className="btn btn-success m-2" style={{backgroundColor:"#029c14"}}><a href="/sign-up" style={{color:"#fff"}}>Sign_UP</a></button>
                    <button className="btn btn-info m-2" style={{backgroundColor:"#0330fc"}}><a href="/sign-in" style={{color:"#fff"}}>Sign_IN</a></button>
                </div>

            </div>
        );
    }
}

Home.propTypes = {};

export default Home;