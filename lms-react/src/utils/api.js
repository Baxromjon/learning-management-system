export const api={

    register:'/auth/register',
    login:'/auth/login',
    userMe:'/user/me',

    //ROLE
    getAllRole:'/role/get_all_roles',

    //GENDER
    getAllGender:'/gender/get_all_gender',

    //COURSES
    getAllCourses:'/course/get_all_courses',
    getAllCoursesByMentor:'/course/get_all_courses_by_mentor',
    addCourse:'/course/add_course',

    //MODULES
    getAllGroup:'/group/get_all_group',
    getAllGroupByMentor:'/group/get_all_group_by_mentor',
    addGroup:'/group/add_group',

    //LESSON
    getAllLesson:'/lesson/get_all_lesson',
    getAllLessonByMentor:'/lesson/get_all_lesson_by_mentor',
    addLesson:'/lesson/add_lesson',

    //TASK
    getAllTask:'/task/get_all_task',
    getAllTaskByMentor:'/task/get_all_task_by_mentor',
    addTask:'/task/add_task',

    //ATTACHMENT
    addFile:'/photo/add_photo',





}