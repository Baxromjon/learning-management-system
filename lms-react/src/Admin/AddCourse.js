import React, {useState} from 'react';
import {createPortal} from "react-dom";

function AddCourse(props) {
    const {toggle, showModal} = props
    const [isOpen, setIsOpen] = useState(true);
    const modalElement = document.getElementById('modal-root')
    return createPortal()

        ;
}

AddCourse.propTypes = {};

export default AddCourse;