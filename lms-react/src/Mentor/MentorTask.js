import React, {Component, useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";
import {useHistory} from "react-router-dom";
import {TOKEN} from "../utils/constant";

function MentorTask() {
    const [tasks, setTasks] = useState([]);
    const history = useHistory();


    useEffect(() => {
        if (localStorage.getItem(TOKEN)) {
            getAllTask()
        } else {
            history.push("/sign-in")
        }
    }, [])

    const getAllTask = () => {
        request({
            url: api.userMe,
            method: 'GET'
        }).then(res => {
            let userId = res.data.data.id
            request({
                url: api.getAllTaskByMentor + '/' + userId,
                method: 'GET'
            }).then(res => {
                console.log(res.data.data);
                setTasks(res.data.data)
            }).catch(err => {
            })
        })

    }

    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <div className="topnav">
                <a href="/mentor">Courses</a>
                <a href="/mentor-modules">Modules</a>
                <a href="/mentor-lessons">Lessons</a>
                <a className="active" href="/mentor-tasks">Tasks</a>
            </div>
            <br/>
            <button className="btn btn-info">qo`shish</button>
            <br/>
            <div>
                <table className="table table-hover">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Task title</th>
                        <th>Must complete</th>
                        <th>Task Priority</th>
                        <th>Lesson title</th>
                    </tr>
                    </thead>
                    <tbody>
                    {tasks?.map((item, index) =>
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{item.title}</td>
                            <td>{item.mustComplete ? "true" : "False"}</td>
                            <td>{item.priority}</td>
                            <td>{item.lesson.title}</td>
                        </tr>
                    )}
                    </tbody>
                </table>

            </div>
        </div>
    );
}

MentorTask.propTypes = {};

export default MentorTask;