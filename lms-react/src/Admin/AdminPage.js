import React, {Component, useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";

function AdminPage() {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        getAllCourses()
    }, [])

    const getAllCourses = () => {
        request({
            url: api.getAllCourses,
            method: 'GET'
        }).then(res => {
            console.log(res.data.data);
            setCourses(res.data.data)
        }).catch(err => {
        })
    }

    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <div className="topnav">
                <a className="active" href="/admin">Courses</a>
                <a href="/modules">Modules</a>
                <a href="/lessons">Lessons</a>
                <a href="/tasks">Tasks</a>
            </div>
            <br/>
            <button className="btn btn-info">qo`shish</button>
            <br/>

            <div>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>№</th>
                            <th>Course title</th>
                            <th>Course level</th>
                            <th>Course mentor</th>
                        </tr>
                    </thead>
                    <tbody>
                    {courses?.map((item, index)=>
                        <tr key={index}>
                            <td>{index+1}</td>
                            <td>{item.name}</td>
                            <td>{item.level}</td>
                            <td>{item.mentorId.firstName+" "+item.mentorId.lastName}</td>
                        </tr>
                    )}
                    </tbody>
                </table>

            </div>
        </div>
    );
}

AdminPage.propTypes = {};

export default AdminPage;