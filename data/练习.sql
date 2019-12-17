select section.section_id,course.course_id,
course.course_name,course.credit,instructor.inst_name,
classroom.classroom_id,sec_time_slot.time_slot_id,examination.date,examination.exam_start,
examination.exam_end
 from section ,sec_course  ,  course, teaches, 
 instructor,classroom,sec_classroom, sec_time_slot, examination, sec_exam
                 where 
                 section.section_id= sec_course.section_id and
                 sec_course.course_id= course.course_id  and 
				 teaches.section_id= section.section_id and 
                 teaches.instructor_id= instructor.instructor_id and
                 sec_exam.exam_id =examination.exam_id and
                 sec_exam.section_id =section.section_id and
                 sec_classroom.section_id= section.section_id and
                 sec_classroom.classroom_id= classroom.classroom_id and
                 sec_time_slot.section_id =section.section_id and
                course.course_name like '%Image%'
                