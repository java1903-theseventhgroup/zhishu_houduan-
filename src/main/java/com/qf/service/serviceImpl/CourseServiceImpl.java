package com.qf.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.qf.dao.CourseDao;
import com.qf.domain.Course;
import com.qf.response.CommonCode;
import com.qf.response.QueryResponseResult;
import com.qf.response.QueryResult;
import com.qf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Override
    public QueryResponseResult findAllCourse() {                                //查询课程表全部
        List<Course> all = courseDao.findAllCourse();

        QueryResult<Course> userQueryResult = new QueryResult<>();
        userQueryResult.setList(all);

        return new QueryResponseResult<>(CommonCode.SUCCESS,userQueryResult);

    }

    @Override
    public QueryResponseResult findS22(String classification) {
        List<Course> s22 = courseDao.findS22(classification);
        QueryResult<Course> queryResult=new QueryResult<>();
            queryResult.setList(s22);
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);

    }

    @Override
    public QueryResponseResult findSujectsByName(String subject) {
        List<Course> sujectsByName = courseDao.findSujectsByName(subject);
        QueryResult<Course> queryResult=new QueryResult<>();
        queryResult.setList(sujectsByName);
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    /**
     *          查询全部课程表
     * @return
     */
    @Override
    public QueryResponseResult findAll() {

        List<Course> all = courseDao.findAll();
        QueryResult<Course> userQueryResult = new QueryResult<>();
        userQueryResult.setList(all);
        return new QueryResponseResult<>(CommonCode.SUCCESS,userQueryResult);
    }

    /**
     *         查询能力学院全部课程
     * @return
     */
    @Override
    public QueryResponseResult findByAbility(String courseCollege) {

        List<Course> listByAbility = courseDao.findByAbility(courseCollege);
        QueryResult<Course> userQueryResult = new QueryResult<>();
        userQueryResult.setList(listByAbility);
        return new QueryResponseResult<>(CommonCode.SUCCESS,userQueryResult);
    }

    /**
     * //专题首页过滤返回 （首页图片，上架时间，书标题，）
     * @return
     */
    @Override
    public QueryResponseResult findHomePage() {
        List<Course> listByAbility = courseDao.findHomePage();
        QueryResult<Course> userQueryResult = new QueryResult<>();
        userQueryResult.setList(listByAbility);
        return new QueryResponseResult<>(CommonCode.SUCCESS,userQueryResult);
    }

    @Override
    public QueryResponseResult findByNameOrAuthor(String subject) {
        List<Course> all = courseDao.findByNameOrAuthor(subject);
        QueryResult<Course> userQueryResult = new QueryResult<>();
        userQueryResult.setList(all);
        return new QueryResponseResult<>(CommonCode.SUCCESS,userQueryResult);
    }






    @Override
//查询总行数
    public int selectRows(Integer rows) {
        int i = courseDao.selectRows();
        return i%rows>0?i/rows+1:i/rows;
    }
    @Override
//后台查询所有用户
    public List<Course> findAll(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        return courseDao.findAll(page,rows);
    }
    @Override
    public boolean inserCourse(Course course) {
        return courseDao.inserCourse(course)>0?true:false;
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        return courseDao.deleteCourse(courseId)>0?true:false;
    }

    @Override
    public boolean uploadCourseTitleImgurl(Course course) {
        return courseDao.uploadCourseTitleImgurl(course)>0?true:false;
    }

    @Override
    public boolean uploadCourseOtherImgurl(Course course) {
        return courseDao.uploadCourseOtherImgurl(course)>0?true:false;
    }

    @Override
    public boolean uploadCourseTableImgurl(Course course) {
        return courseDao.uploadCourseTableImgurl(course)>0?true:false;
    }

    @Override
    public boolean uploadcourseImgurl(Course course) {
        return courseDao.uploadcourseImgurl(course)>0?true:false;
    }

    @Override
    public Course loadById(int courseId) {              //修改（根据id查）
        return courseDao.loadById(courseId);
    }

    @Override
    public Integer updateCourse(Course course) {         //修改
        return courseDao.updateCourse(course);
    }
}
