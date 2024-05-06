package lt.vu.mybatis.dao;

import jdk.jfr.Name;
import lt.vu.entities.Player;
import lt.vu.entities.Sponsor;
import lt.vu.entities.Team;
import org.apache.ibatis.annotations.*;
import org.mybatis.cdi.Mapper;

import javax.inject.Named;
import java.util.List;

@Mapper
public interface SponsorMapper {

    @Select("SELECT * FROM SPONSOR")
    List<Sponsor> selectAll();

    @Select("SELECT p.* FROM TEAM p " +
            "JOIN TEAM_SPONSOR pt ON p.ID = pt.TEAM_ID " +
            "JOIN SPONSOR t ON pt.SPONSOR_ID = t.ID " +
            "WHERE t.ID = #{sponsorId}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "jerseyNumber", column = "JERSEY_NUMBER"),
            // Include other Player properties if they exist
    })
    List<Team> selectTeamsBySponsorId(Integer sponsorId);
}
