import React, {Component, useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";
import {useHistory} from "react-router-dom";
import {TOKEN} from "../utils/constant";
import {Modal, ModalBody, ModalHeader, ModalFooter} from "reactstrap";
import {useForm} from "react-hook-form";

function MentorModule() {
    const [modules, setModules] = useState([]);
    const [courses, setCourses] = useState([]);
    const [currentUser, setCurrentUser] = useState('');
    const history = useHistory();
    const [showModal, setShowModal] = useState(false);
    const {register, handleSubmit, watch, formState: {errors}} = useForm();

    const hideModal = () => {
        setShowModal(!showModal)
    }
    useEffect(() => {
        if (localStorage.getItem(TOKEN)) {
            getAllModules()
            getAllCourses()
        } else {
            history.push("/sign-in")
        }
    }, [])

    const getAllModules = () => {
        request({
            url: api.userMe,
            method: 'GET'
        }).then(res => {
            let userId = res.data.data.id
            setCurrentUser(userId);
            request({
                url: api.getAllGroupByMentor + "/" + userId,
                method: 'GET'
            }).then(res => {
                // console.log(res.data.data);
                setModules(res.data.data)
            }).catch(err => {
            })
        })
    }

    const getAllCourses=()=>{
        request({
            url: api.userMe,
            method: 'GET'
        }).then(res => {
            let userId = res.data.data.id
            request({
                url: api.getAllCoursesByMentor + "/" + userId,
                method: 'GET'
            }).then(res => {
                // console.log(res.data.data);
                setCourses(res.data.data)
            }).catch(err => {
            })
        })
    }

    function addModule(e, v) {
        let ModuleDTO={
            title:'',
            description:'',
            inviteLink:'',
            courseId:'',
            userId:[],
            price:''
        }
        ModuleDTO.title=e.title;
        ModuleDTO.description=e.description;
        ModuleDTO.inviteLink=e.inviteLink;
        ModuleDTO.courseId=e.courseId;
        ModuleDTO.userId=[currentUser];
        ModuleDTO.price=e.price;
        console.log(ModuleDTO);
        request({
            url:api.addGroup,
            method:'POST',
            data:ModuleDTO
        }).then(res=>{
            getAllModules()
            hideModal()
        }).catch(err=>{})
    }

    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <div className="topnav">
                <a href="/mentor">Courses</a>
                <a className="active" href="/mentor-modules">Modules</a>
                <a href="/mentor-lessons">Lessons</a>
                <a href="/mentor-tasks">Tasks</a>
            </div>
            <br/>
            <button className="btn btn-info" onClick={hideModal}>ADD MODAL</button>
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
            <Modal isOpen={showModal}>
                <ModalHeader>Add Module</ModalHeader>
                <ModalBody>
                    <form onSubmit={handleSubmit(addModule)}>
                        <div className="row">
                            <div className="col-md-6">
                                <label>Module title</label>
                                <input className="form-control form-control-lg" defaultValue="" {...register("title")} />
                            </div>
                            <div className="col-md-6">
                                <label>Module Price</label>
                                <input className="form-control form-control-lg" type="number"
                                       defaultValue="" {...register("price")} />
                            </div>
                        </div>

                        <div className="form-group">
                            <label>Description</label>
                            <input className="form-control form-control-lg"
                                   defaultValue="" {...register("description")} />
                        </div>
                        <div className="form-group">
                            <label>Invite Link</label>
                            <input className="form-control form-control-lg"
                                   defaultValue="" {...register("inviteLink")} />
                        </div>

                        <div className="form-group">
                            <label>Select Course</label>
                            <select className="form-control form-control-lg" {...register("courseId")}>
                                {courses?.map(item=>
                                    <option value={item.id}>{item.name}</option>
                                )}
                            </select>
                        </div>

                        <button className="btn btn-success m-1" type="submit">ADD</button>
                        <button className="btn btn-danger m-1" onClick={hideModal}>CANCEL</button>
                    </form>
                </ModalBody>
            </Modal>
        </div>
    );
}

MentorModule.propTypes = {};

export default MentorModule;