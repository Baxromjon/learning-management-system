import React, {useEffect, useState} from 'react';
import "./main.css"
import request from "../utils/request";
import {api} from "../utils/api";
import {TOKEN} from "../utils/constant";
import {useHistory} from "react-router-dom";
import {Modal, ModalBody, ModalHeader} from "reactstrap";
import {useForm} from "react-hook-form";

function MentorPage() {
    const [currentUser, setCurrentUser] = useState([]);
    const [courses, setCourses] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [currentCourse, setCurrentCourse] = useState('');
    const history = useHistory();
    const {register, handleSubmit, watch, formState: {errors}} = useForm();


    useEffect(() => {
        if (localStorage.getItem(TOKEN)) {
            getCurrentUser()
            getAllCourses()
        } else {
            history.push("/sign-in")
        }
    }, [])

    const getAllCourses = () => {
        request({
            url: api.userMe,
            method: 'GET'
        }).then(res => {
            let userId = res.data.data.id
            request({
                url: api.getAllCoursesByMentor + "/" + userId,
                method: 'GET'
            }).then(res => {
                setCourses(res.data.data)
            }).catch(err => {
            })
        }).catch(err => {
            alert("Error")
        })

    }

    const hideModal = () => {
        console.log(showModal)
        setShowModal(!showModal)
    }
    const getCurrentUser = () => {
        request({
            url: api.userMe,
            method: 'GET'
        }).then(res => {
            setCurrentUser(res.data.data)
        })
    }
    const addCourse = (e, v) => {
        console.log(e)
        let CourseDTO = {
            name: "",
            description: "",
            userId: "",
            level: ""
        }
        CourseDTO.name = e.name;
        CourseDTO.userId = currentUser.id;
        CourseDTO.description = e.description;
        CourseDTO.level = e.level;
        request({
            url: api.addCourse,
            method: 'POST',
            data: CourseDTO
        }).then(res => {
            hideModal()
            getAllCourses()
        }).catch(err => {
        })
    }

    return (
        <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
            <div className="topnav">
                <a className="active" href="/mentor">Courses</a>
                <a href="/mentor-modules">Modules</a>
                <a href="/mentor-lessons">Lessons</a>
                <a href="/mentor-tasks">Tasks</a>
            </div>
            <br/>
            <button className="btn btn-info" style={{backgroundColor: "#0dcaf0"}} onClick={hideModal}>ADD COURSE
            </button>
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
                    {courses?.map((item, index) =>
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{item.name}</td>
                            <td>{item.level}</td>
                            <td>{item.mentorId.firstName + " " + item.mentorId.lastName}</td>
                        </tr>
                    )}
                    </tbody>
                </table>

            </div>
            <Modal isOpen={showModal}>
                <ModalHeader>Add Course</ModalHeader>
                <ModalBody>
                    <form onSubmit={handleSubmit(addCourse)}>
                        <div className="form-group">
                            <label>Course title</label>
                            <input className="form-control form-control-lg" defaultValue="" {...register("name")} />
                        </div>
                        <div className="form-group">
                            <label>Description</label>
                            <input className="form-control form-control-lg"
                                   defaultValue="" {...register("description")} />
                        </div>
                        <div className="form-group">
                            <label>Course level</label>
                            <select {...register("level")} className="form-control form-control-lg">
                                <option value="EASY">EASY</option>
                                <option value="MEDIUM">MEDIUM</option>
                                <option value="HARD">HARD</option>
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

MentorPage.propTypes = {};

export default MentorPage;