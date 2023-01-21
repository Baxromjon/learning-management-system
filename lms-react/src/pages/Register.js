import React, {Component} from 'react';
import "./register.css"

class Register extends Component {
    render() {
        return (

            <div className="container">
                <form action="#">
                    <div className="row justify-content-md-center">
                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                            <div className="login-screen">
                                <div className="login-box">
                                    <a href="#" className="login-logo">
                                        <img src="//ssl.gstatic.com/accounts/ui/logo_2x.png"
                                             alt="Bootdey bootstrap snippets bootdey"/>
                                    </a>
                                    <div className="row gutters">
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <input type="text" className="form-control" placeholder="First Name"/>
                                            </div>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <input type="text" className="form-control" placeholder="Last Name"/>
                                            </div>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <input type="email" className="form-control" placeholder="Email ID"/>
                                            </div>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <input type="password" className="form-control" placeholder="Password"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="actions clearfix">
                                        <button type="submit" className="btn btn-primary btn-block">Signup</button>
                                    </div>
                                    <div className="or">
                                        <span>or signup using</span>
                                    </div>
                                    <div className="row gutters">
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <button type="submit" className="btn btn-tw btn-block">Twitter</button>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <button type="submit" className="btn btn-fb btn-block">Facebook</button>
                                        </div>
                                    </div>
                                    <a href="#" className="additional-link">Have an Account? <span>Login Now</span></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

Register.propTypes = {};

export default Register;