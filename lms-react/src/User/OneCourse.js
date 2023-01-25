import React, {useEffect, useState} from 'react';
import {CURRENT_COURSE} from "../utils/constant";
import request from "../utils/request";
import {api} from "../utils/api";
import {Modal, ModalBody, ModalHeader} from 'reactstrap';

function OneCourse() {
    const [currentCourse] = useState(localStorage.getItem(CURRENT_COURSE))
    const [modules, setModules] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [currentModule, setCurrentModule] = useState('');
    const [currentUser, setCurrentUser] = useState('');
    useEffect(() => {
        getAllModule()
        getUserMe()
    }, [])

    const hideModal = (item) => {
        setShowModal(!showModal)
        setCurrentModule(item)
        console.log(item)
    }
    const getUserMe = () => {
        request({
            url: api.userMe,
            method: 'GET'
        }).then(res => {
            setCurrentUser(res.data.data)
        })
    }
    const buyGroup = () => {
        let BuyGroup = {
            userId: '',
            groupId: ''
        }
        BuyGroup.groupId = currentModule.id;
        BuyGroup.userId = currentUser.id;
        console.log(BuyGroup);
        request({
            url: api.buyModule,
            method: 'POST',
            data: BuyGroup
        }).then(res => {
            hideModal()
            getAllModule()
        }).catch(er => {

        })
    }
    const getAllModule = () => {
        request({
            url: api.getByCourse + '/' + currentCourse,
            method: 'GET'
        }).then(res => {
            setModules(res.data.data)
        }).catch(err => {
        })
    }
    return (
        <div className="container h-80">
            <h1 style={{textAlign: "center"}}>get about one course</h1>
            <table className="table table-hover" style={{marginLeft: "10px"}}>
                <thead>
                <tr>
                    <th>№</th>
                    <th>Module name</th>
                    <th>Module price</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {modules?.map((item, index) =>
                    <tr key={index}>
                        <td>{index + 1}</td>
                        <td>{item.title}</td>
                        <td>{item.price + " $"}</td>
                        <td>
                            <button className="btn btn-info"
                                    onClick={() => hideModal(item)}>BUY
                            </button>
                        </td>
                    </tr>
                )}
                </tbody>
            </table>
            <Modal isOpen={showModal}>
                <ModalHeader>Do you want to buy this Module {currentModule.title}</ModalHeader>
                <div className="row" style={{marginLeft: "auto", marginRight: "auto"}}>
                    <button className="btn btn-success m-2" type="submit" onClick={buyGroup}>YES</button>
                    <button className="btn btn-danger m-2" onClick={hideModal}>NO</button>
                </div>

            </Modal>
        </div>
    );
}

OneCourse.propTypes = {};

export default OneCourse;