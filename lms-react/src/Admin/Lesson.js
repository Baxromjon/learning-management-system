import React, {Component, useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";
import {useHistory} from "react-router-dom";
import {TOKEN} from "../utils/constant";

function Lesson() {
    const [lessons, setLessons]=useState([]);
    const history = useHistory();

    useEffect(()=>{
        if (localStorage.getItem(TOKEN)){
            getAllLesson()
        }else {
            history.push("/sign-in")
        }
    },[])

    const getAllLesson=()=>{
        request({
            url:api.getAllLesson,
            method:'GET'
        }).then(res=>{
            console.log(res.data.data);
            setLessons(res.data.data)
        }).catch(err=>{})
    }


    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <div className="topnav">
                <a href="/admin">Courses</a>
                <a href="/modules">Modules</a>
                <a className="active" href="/lessons">Lessons</a>
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
                        <th>Lesson title</th>
                        <th>Module title</th>
                        <th>URL</th>
                    </tr>
                    </thead>
                    <tbody>
                    {lessons?.map((item, index)=>
                    <tr key={index}>
                        <td>{index+1}</td>
                        <td>{item.title}</td>
                        <td>{item.group.title}</td>
                        <td><a href={item.url}>{item.url}</a></td>
                    </tr>
                    )}
                    </tbody>
                </table>

            </div>
        </div>
    );
}

Lesson.propTypes = {};

export default Lesson;