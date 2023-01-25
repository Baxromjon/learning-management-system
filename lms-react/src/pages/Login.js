import React, {Component, useEffect} from 'react';
import "./login.css";
import {useForm} from 'react-hook-form'
import request from "../utils/request";
import {api} from "../utils/api";
import {CURRENTUSER, TOKEN} from "../utils/constant";
import {useHistory} from 'react-router-dom';


function Login(){
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const history = useHistory();

    const login=(e)=>{
        request({
            url:api.login,
            method:'POST',
            data:e
        }).then(res=>{
            if (res.status===200){
                // console.log(res)
                localStorage.setItem(TOKEN, 'Bearer '+res.data.data)
                request({
                    url:api.userMe,
                    method:'GET'
                }).then(ress=>{
                    localStorage.setItem(CURRENTUSER, ress.data.data.id)
                    console.log(ress.data.data.id)
                    if (ress.data.data.authorities[0].roleEnum==='ROLE_ADMIN'){
                        history.push("/admin")
                    }else if (ress.data.data.authorities[0].roleEnum==='ROLE_PARENT'){
                        history.push("/parent")
                    }else if (ress.data.data.authorities[0].roleEnum==='ROLE_MENTOR'){
                        history.push("/mentor")
                    }else if (ress.data.data.authorities[0].roleEnum==='ROLE_USER'){
                        history.push("/user")
                    }else if (ress.data.data.authorities[0].roleEnum==='ROLE_SUPER_ADMIN'){
                        history.push("/admin")
                    }
                })
            }
        }).catch(err=>{
            alert("Some error in login!")
        })
    }

    // render() {
        return (
            <div className="container h-100">
                <div className="row h-100">
                    <div className="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
                        <div className="d-table-cell align-middle">

                            <div className="text-center mt-4">
                                <h1 className="h2">Welcome to LMS</h1>
                                <p className="lead">
                                    Sign in to your account to continue
                                </p>
                            </div>

                            <div className="card">
                                <div className="card-body">
                                    <div className="m-sm-4">
                                        <div className="text-center">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png"
                                                 alt="Andrew Jones" className="img-fluid rounded-circle" width="132"
                                                 height="132"/>
                                        </div>
                                        <form onSubmit={handleSubmit(login)}>
                                            <div className="form-group">
                                                <label>Phone Number</label>
                                                <input className="form-control form-control-lg" defaultValue="" {...register("phoneNumber")} />
                                            </div>
                                            <div className="form-group">
                                                <label>Password</label>
                                                <input className="form-control form-control-lg" type="password" defaultValue="" {...register("password")} />
                                                    <small>
                                                        <a>Forgot password?</a>
                                                    </small>
                                            </div>
                                            <div>
                                                <div className="custom-control custom-checkbox align-items-center">
                                                    <input type="checkbox" className="custom-control-input"
                                                           value="remember-me" name="remember-me" checked=""/>
                                                        <label className="custom-control-label text-small">Remember me
                                                            next time</label>
                                                </div>
                                            </div>
                                            <div className="text-center mt-3">
                                                <button className="btn btn-info" type="submit">SIGN IN</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        );
    // }
}

Login.propTypes = {};

export default Login;