import React, {Component, useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";
import {useHistory} from "react-router-dom";
import {TOKEN} from "../utils/constant";

function Module() {
    const [modules, setModules] = useState([]);
    const history = useHistory();

    useEffect(() => {
        if (localStorage.getItem(TOKEN)){
            getAllModules()
        }else {
            history.push("/sign-in")
        }
    }, [])

    const getAllModules = () => {
        request({
            url: api.getAllGroup,
            method: 'GET'
        }).then(res => {
            console.log(res.data.data);
            setModules(res.data.data)
        }).catch(err => {
        })
    }
    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <div className="topnav">
                <a href="/admin">Courses</a>
                <a className="active" href="/modules">Modules</a>
                <a href="/lessons">Lessons</a>
                <a href="/tasks">Tasks</a>
            </div>
            <br/>
            {/*<button className="btn btn-info">qo`shish</button>*/}
            <br/>
            <div>
                <table className="table table-hover">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Module title</th>
                        <th>Course title</th>
                        <th>Module Price</th>
                        <th>Mentor Full name</th>
                        <th>Module link</th>
                    </tr>
                    </thead>
                    <tbody>
                    {modules?.map((item, index) =>
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{item.title}</td>
                            <td>{item.course.name}</td>
                            <td>{item.price}</td>
                            <td>{item.mentors[0].firstName + " " + item.mentors[0].lastName}</td>
                            <td><a href={item.inviteLink}>{item.inviteLink}</a></td>
                        </tr>
                    )}
                    </tbody>
                </table>

            </div>
        </div>
    );
}

Module.propTypes = {};

export default Module;