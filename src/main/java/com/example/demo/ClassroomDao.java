package com.example.demo;

import com.example.demo.entity.Classroom;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class ClassroomDao extends JdbcDaoSupport
{
    public int getCount()
    {
        return getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM CLASSROOMS",Integer.class);
    }

    public static final RowMapper<Classroom> MAPPER = (r, i) -> {

        Classroom classroom = new Classroom();
        classroom.setRoomId(r.getInt("ROOM_ID"));
        classroom.setRoomSize(r.getInt("ROOM_SIZE"));
        classroom.setScreen(r.getString("SCREEN"));

        return classroom;
    };

    public Classroom getOne(int id)
    {
        return getJdbcTemplate().queryForObject("SELECT * FROM CLASSROOMS WHERE ROOM_ID = ?", new Object[]{id}, MAPPER);
    }

    public List<Classroom> getEveryRoomWithScreen()
    {
        return getJdbcTemplate().query("SELECT * FROM CLASSROOMS WHERE SCREEN = 'Y'", MAPPER);
    }

    public int getRoomsBiggerThan(int number)
    {
        return getJdbcTemplate().queryForObject("SELECT COUNT (*) FROM CLASSROOMS WHERE ROOM_SIZE > ?",new Object[]{number}, Integer.class);
    }

    public void create(Classroom classroom)
    {
        getJdbcTemplate().update("insert into classrooms(ROOM_ID, ROOM_SIZE, SCREEN) values(?,?,?)",
                classroom.getRoomId(),
                classroom.getRoomSize()
                ,classroom.getScreen());
    }

    public boolean delete(int id){
        return getJdbcTemplate().update("DELETE FROM classrooms WHERE ROOM_ID = ?", id) == 1;
    }
}
