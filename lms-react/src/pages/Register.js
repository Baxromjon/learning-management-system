import React, {Component, useEffect, useState} from 'react';
import "./register.css"
import request from "../utils/request";
import {api} from "../utils/api";
import {useForm} from 'react-hook-form'
import {useHistory} from "react-router-dom";


function Register() {
    const [gender, setGender] = useState([])
    const [currentGender, setCurrentGender] = useState('')
    const [roles, setRoles] = useState([])
    const [currentRoles, setCurrentRoles] = useState('')
    const {register, handleSubmit, watch, formState: {errors}} = useForm();
    const history = useHistory();

    useEffect(() => {
        getAllRoles()
    }, [])

    const getAllRoles = () => {
        request({
            url: api.getAllRole,
            method: 'GET'
        }).then(ress => {
            setRoles(ress.data.data)
        }).catch(err => {
        })
    }
    const save = (e,v) => {
        console.log(e);
        request({
            url:api.register,
            method:'POST',
            data:e
        }).then(res=>{
            history.push("/sign-in")
        }).catch(err=>{
            alert("Error!")
        })
    }
    // render() {
    return (

        <div className="container">
            {/*<form action="#">*/}
                <div className="row justify-content-md-center">
                    <div className="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
                        <div className="login-screen">
                            <div className="login-box">
                                <a href="#" className="login-logo">
                                    <img src="//ssl.gstatic.com/accounts/ui/logo_2x.png"
                                         alt="Bootdey bootstrap snippets bootdey"/>
                                </a>
                                <form onSubmit={handleSubmit(save)}>
                                    <div className="row gutters">
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 p-1">
                                            <input className="form-control form-control-lg" defaultValue=""
                                                   placeholder="Enter First Name" {...register("firstName")} />
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 p-1">
                                            <input className="form-control form-control-lg" defaultValue=""
                                                   placeholder="Enter Last Name" {...register("lastName")} />
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 p-1">
                                            <input className="form-control form-control-lg" defaultValue=""
                                                   placeholder="Enter Phone Number" {...register("phoneNumber")} />
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 p-1">
                                            <input className="form-control form-control-lg" defaultValue=""
                                                   type="password"
                                                   placeholder="Enter Password" {...register("password")} />
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 p-1">
                                            <input className="form-control form-control-lg" type="date" {...register("birthDate")}/>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 p-1">
                                            <select {...register("gender")}  className="form-control form-control-lg">
                                                <option value="">SELECT GENDER</option>
                                                <option value="MALE">MALE</option>
                                                <option value="FEMALE">FEMALE</option>
                                            </select>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 p-1">
                                            <select {...register("roleId")}  className="form-control form-control-lg">
                                                <option value="">SELECT ROLE</option>
                                                {roles?.map(item =>
                                                    <option value={item.id}
                                                            key={item.id}>{item.roleEnum.substring(5)}</option>
                                                )}
                                            </select>
                                        </div>

                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 p-1">
                                            <input className="form-control form-control-lg" defaultValue=""
                                                   type="text"
                                                   placeholder="Enter Parent Key" {...register("parentKey")} />
                                        </div>
                                    </div><br/>
                                        <button type="submit" className="btn btn-primary btn-block">SIGN-UP</button>
                                    <div className="or">
                                        <span>or signup using</span>
                                    </div>
                                    <div className="row gutters">
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <button type="submit" className="btn btn-tw btn-block">Google</button>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <button type="submit" className="btn btn-fb btn-block">Facebook</button>
                                        </div>
                                    </div>
                                    <a href="#" className="additional-link">Have an Account? <span>Login Now</span></a>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            {/*</form>*/}
        </div>
    );
    // }
}

Register.propTypes = {};

export default Register;