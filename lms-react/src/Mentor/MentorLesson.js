import React, {Component, useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";
import {useHistory} from "react-router-dom";
import {TOKEN} from "../utils/constant";
import {useForm} from "react-hook-form";
import {Modal, ModalBody, ModalHeader} from "reactstrap";

function MentorLesson() {
    const [lessons, setLessons] = useState([]);
    const [module, setModule] = useState([]);
    const [currentFile, setCurrentFile] = useState([]);
    const history = useHistory();
    const [showModal, setShowModal] = useState(false);
    const {register, handleSubmit, watch, formState: {errors}} = useForm();

    useEffect(() => {
        if (localStorage.getItem(TOKEN)) {
            getAllLesson()
            getAllModule()
        } else {
            history.push("/sign-in")
        }
    }, [])

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

    const getAllModule = () => {
        request({
            url: api.userMe,
            method: 'GET'
        }).then(res => {
            let userId = res.data.data.id
            request({
                url: api.getAllGroupByMentor + '/' + userId,
                method: 'GET'
            }).then(res => {
                setModule(res.data.data)
            }).catch(err => {
            })
        })
    }

    const hideModal = () => {
        console.log(showModal)
        setShowModal(!showModal)
    }
    const uploadFile = (file) => {
        let formData = new FormData();
        formData.append("file", file.target.files[0])
        console.log(file)
        request({
            url: api.addFile,
            method: 'POST',
            data: formData
        }).then(res => {
            setCurrentFile(res.data)
        }).catch(err => {
        })
    }
    const addLesson = (e, v) => {
        let LessonDTO = {
            url: '',
            title: '',
            groupId: '',
            lessonNumber: '',
            videoId: ''
        }
        LessonDTO.lessonNumber = e.lessonNumber;
        LessonDTO.title = e.title;
        LessonDTO.url = e.url;
        LessonDTO.groupId = e.groupId;
        LessonDTO.videoId = currentFile;
        console.log(LessonDTO);
        request({
            url: api.addLesson,
            method: 'POST',
            data: LessonDTO
        }).then(res => {
            getAllLesson()
            hideModal()
        }).catch(err => {
        })
    }


    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <div className="topnav">
                <a href="/mentor">Courses</a>
                <a href="/mentor-modules">Modules</a>
                <a className="active" href="/mentor-lessons">Lessons</a>
                <a href="/mentor-tasks">Tasks</a>
            </div>
            <br/>
            <button className="btn btn-info" onClick={hideModal}>ADD LESSON</button>
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
                    {lessons?.map((item, index) =>
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{item.title}</td>
                            <td>{item.group.title}</td>
                            <td><a href={item.url}>{item.url}</a></td>
                        </tr>
                    )}
                    </tbody>
                </table>

            </div>
            <Modal isOpen={showModal}>
                <ModalHeader>Add Lesson</ModalHeader>
                <ModalBody>
                    <form onSubmit={handleSubmit(addLesson)}>
                        <div className="form-group">
                            <label>Lesson Title</label>
                            <input className="form-control form-control-lg" type="text"
                                   defaultValue="" {...register("title")} />
                        </div>
                        <div className="form-group">
                            <label>Index Lesson</label>
                            <input className="form-control form-control-lg" type="number"
                                   defaultValue="" {...register("lessonNumber")} />
                        </div>
                        <div className="form-group">
                            <label>URL</label>
                            <input className="form-control form-control-lg" type="text"
                                   defaultValue="" {...register("url")} />
                        </div>
                        <div className="form-group">
                            <label>Select Course</label>
                            <select className="form-control form-control-lg" {...register("groupId")}>
                                {module?.map(item =>
                                    <option value={item.id}>{item.title}</option>
                                )}
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Upload file</label><br/>
                            {<video width="320" height="240" controls className="m-1">
                                <source src={'http://192.168.0.218:8080/api/photo/get/' + currentFile}/>
                            </video>}
                            <input className="mt-3" {...register("videoId", {required: true})} type="file"
                                   accept='video/*' onChange={uploadFile}/>
                        </div>
                        <button className="btn btn-success m-1" type="submit">ADD</button>
                        <button className="btn btn-danger m-1" onClick={hideModal}>CANCEL</button>
                    </form>
                </ModalBody>
            </Modal>
        </div>
    );
}

MentorLesson.propTypes = {};

export default MentorLesson;