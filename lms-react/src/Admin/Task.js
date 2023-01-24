import React, {Component, useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";

function Task() {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        getAllTask()
    }, [])

    const getAllTask = () => {
        request({
            url: api.getAllTask,
            method: 'GET'
        }).then(res => {
            console.log(res.data.data);
            setTasks(res.data.data)
        }).catch(err => {
        })
    }

    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <div className="topnav">
                <a href="/admin">Courses</a>
                <a href="/modules">Modules</a>
                <a href="/lessons">Lessons</a>
                <a className="active" href="/tasks">Tasks</a>
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
                    {tasks?.map((item, index)=>
                    <tr key={index}>
                        <td>{index+1}</td>
                        <td>{item.title}</td>
                        <td>{item.mustComplete?"true":"False"}</td>
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

Task.propTypes = {};

export default Task;