import React, {Component, useEffect, useState} from 'react';
import {CURRENT_COURSE, CURRENTUSER, TOKEN} from "../utils/constant";
import {useHistory} from "react-router-dom";
import request from "../utils/request";
import {api} from "../utils/api";

function UserPage() {
    const [courses, setCourses] = useState([]);
    const history = useHistory();
    const [currentUser, setCurrentUser] = useState('')
    const [cash, setCash] = useState([]);
    const [currentUserId]=useState(localStorage.getItem(CURRENTUSER))

    useEffect(() => {
        if (localStorage.getItem(TOKEN)) {
            getUserById()
            getUserCash()
            getAllCourses()
        } else {
            history.push("/sign-in")
        }
    }, [])

    const getAllCourses = () => {
        request({
            url: api.getAllCourses,
            method: 'GET'
        }).then(res => {
            setCourses(res.data.data)
        }).catch(err => {
        })
    }
    const getOneCourse = (item) => {
        console.log(item.id)
        localStorage.setItem(CURRENT_COURSE, item.id);
        console.log(CURRENT_COURSE)
        history.push("/course-main")
    }

    const getUserCash = () => {
            let userId=currentUserId
            request({
                url: api.getUserCash + '/' + userId,
                method: 'GET'
            }).then(res => {
                setCash(res.data)
            })
    }

    const getUserById=()=>{
        request({
            url:api.getUserById+'/'+currentUserId,
            method:'GET'
        }).then(res=>{
            setCurrentUser(res.data)
        })
    }
    return (
        <div>
            <div className="container">
                <div className="row gutters">
                    <div className="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                        <div className="card h-100">
                            <div className="card-body">
                                <div className="account-settings">
                                    <div className="user-profile">{console.log(currentUser)}
                                        {/*<div className="user-avatar">*/}
                                        {/*    <img src="https://bootdey.com/img/Content/avatar/avatar7.png"*/}
                                        {/*         alt="Maxwell Admin"/>*/}
                                        {/*</div>*/}
                                        <h5 className="user-name">{currentUser.firstName+" "+currentUser.lastName}</h5>
                                        <h6 className="user-email">phone number: {currentUser.phoneNumber}</h6>
                                        {/*<h6 className="user-birth_date">birth date: {currentUser.birthDate.split('T')[0]}</h6>*/}
                                        {/*<h6 className="user-birth_date">gender: {currentUser.gender.gender}</h6>*/}
                                        {/*<h6 className="user-birth_date">parent: {currentUser.parentId.firstName+" "+currentUser.parentId.lastName}</h6>*/}
                                        {/*<h6 className="user-birth_date">parent phone: {currentUser.parentId.phoneNumber}</h6>*/}
                                    </div>
                                    <div className="about">
                                        <h5>My Cash:</h5>
                                        <h1>{cash.amount+"$"}</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">

                        <div className="card h-100">
                            <div className="card-body">
                                <div className="container py-5">
                                    <div className="row justify-content-left">
                                        {courses?.map((item, index) =>
                                            <div className="card"
                                                 style={{width: "200px", cursor: "pointer", display: "flex", margin: "5px", backgroundColor:"#fff"}}>
                                                <div className="card-body">
                                                    <h4 className="card-title">{item.name}</h4>
                                                    <p className="card-text">{item.description}</p>
                                                    <button className="btn btn-primary" onClick={() => getOneCourse(item)}>
                                                        See Course
                                                    </button>

                                                </div>
                                            </div>
                                        )}
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            </div>


        </div>
    );
}

UserPage.propTypes = {};

export default UserPage;