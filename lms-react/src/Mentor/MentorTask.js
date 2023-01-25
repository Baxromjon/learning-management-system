import React, {Component, useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";
import {useHistory} from "react-router-dom";
import {TOKEN} from "../utils/constant";
import {ModalBody, Modal, ModalHeader} from "reactstrap";
import {useForm} from "react-hook-form";

function MentorTask() {
    const [tasks, setTasks] = useState([]);
    const [lessons, setLessons] = useState([]);
    const history = useHistory();
    const {register, handleSubmit, watch, formState: {errors}} = useForm();
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        if (localStorage.getItem(TOKEN)) {
            getAllTask()
            getAllLesson()
        } else {
            history.push("/sign-in")
        }
    }, [])
    const hideModal = () => {
        setShowModal(!showModal)
    }
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
    const getAllLesson = () => {
        request({
            url: api.userMe,
            method: 'GET'
        }).then(res => {
            let userId = res.data.data.id
            request({
                url: api.getAllLessonByMentor + '/' + userId,
                method: 'GET'
            }).then(res => {
                setLessons(res.data.data)
            }).catch(err => {
            })
        })

    }

    const saveTask = (e, v) => {
        console.log(e)
        request({
            url: api.addTask,
            method: 'POST',
            data: e
        }).then(res => {
            getAllTask()
            hideModal()
        }).catch(err => {
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
            <button className="btn btn-info" onClick={hideModal}>ADD TASK</button>
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
            <Modal isOpen={showModal}>
                <ModalHeader>Add Task</ModalHeader>
                <ModalBody>
                    <form onSubmit={handleSubmit(saveTask)}>
                        <div className="row">
                            <div className="col-md-9">
                                <label>Task title</label>
                                <input className="form-control form-control-lg"
                                       defaultValue="" {...register("title")} />
                            </div>
                            <div className="col-md-3">
                                <label>Task Number</label>
                                <input className="form-control form-control-lg" type="number"
                                       defaultValue="" {...register("taskNumber")} />
                            </div>
                        </div>

                        <div className="form-group">
                            <label>URL</label>
                            <input className="form-control form-control-lg" type="text"
                                   defaultValue="" {...register("url")} />
                        </div>
                        <div className="form-group">
                            <label>Select Lesson</label>
                            <select className="form-control form-control-lg" {...register("lessonId")}>
                                {lessons?.map(item =>
                                    <option value={item.id}>{item.title}</option>
                                )}
                            </select>
                        </div>
                        <div className="row">
                            <div className="col-md-6">
                                <label>Task Priority</label>
                                <input className="form-control form-control-lg"
                                       defaultValue="" {...register("priority")} />
                            </div>
                            <div className="col-md-6">
                                <label>Must Complete</label>
                                <select {...register("mustComplete")} className="form-control form-control-lg">
                                    <option value="true">TRUE</option>
                                    <option value="false">FALSE</option>
                                </select>
                            </div>
                        </div>
                        <div className="form-group">
                            <label>Description</label><br/>
                            <textarea name="description" id="" cols="60" rows="3"></textarea>
                        </div>
                        <button className="btn btn-success m-1" type="submit">ADD</button>
                        <button className="btn btn-danger m-1" onClick={hideModal}>CANCEL</button>
                    </form>
                </ModalBody>
            </Modal>
        </div>
    );
}

MentorTask.propTypes = {};

export default MentorTask;