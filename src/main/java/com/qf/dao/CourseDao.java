package com.qf.dao;

import com.qf.domain.Course;
import com.qf.response.QueryResponseResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseDao {
        List<Course> findAllCourse();    //查询全部
        List<Course> findS22(String classification);
        List<Course> findSujectsByName(String subject);
        List<Course> findAll();                                             //查询全部
        List<Course> findByAbility(String courseCollege);                   //根据能力学院查询课程
        List<Course> findHomePage();
        public List<Course> findByNameOrAuthor(@Param("subject") String subject); //模糊查询
        Integer inserCourse(Course course);
        Integer deleteCourse(Integer courseId);
        Integer uploadCourseTitleImgurl(Course course);
        List<Course> findAll(Integer page,Integer rows);//加载所有听书
        int selectRows();

        Integer uploadCourseOtherImgurl(Course course);
        Integer uploadCourseTableImgurl(Course course);
        Integer uploadcourseImgurl(Course course);

        Course loadById (int courseId);                                      //修改(根据id查询)
        Integer updateCourse(Course course);


}
