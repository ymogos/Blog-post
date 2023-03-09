package com.yohannes.blogpost.dao;
import com.yohannes.blogpost.models.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Yohannes
 */
@Repository
public class PostDaoDB implements PostDao {
    
    @Autowired
    JdbcTemplate jdbc;

	@Override
	public Post getPostById(int id) {
		try {
            final String SELECT_POST_BY_ID = "SELECT * FROM posts WHERE id = ?";
            Post post = jdbc.queryForObject(SELECT_POST_BY_ID, new HeroMapper(), id);
            return post;
        } catch (DataAccessException ex) {
            return null;
        }
	}
	
	
	public static final class HeroMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int index) throws SQLException {
            Post post = new Post();
            post.setId(rs.getInt("id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setCreated_date(rs.getDate("created_date"));
            post.setAdded_by(rs.getString("added_by"));
            return post;
        }
    }

	@Override
	public List<Post> getAllPosts() {
		   final String SELECT_ALL_HEROS = "SELECT * FROM posts";
	        List<Post> posts = jdbc.query(SELECT_ALL_HEROS, new HeroMapper());
	        return posts;
	}

	@Override
	public Post addPost(Post post) {
		final String INSERT_POST = "INSERT INTO posts(content, created_date, title,added_by) "
                + "VALUES(?,?,?,?)";
          jdbc.update(INSERT_POST,
                post.getContent(),
                post.getCreated_date(),
                post.getTitle(),
                post.getAdded_by());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        post.setId(newId);
        return post;
	}

	@Override
	public void updatePost(Post post) {
		
		final String UPDATE_POST = "UPDATE posts SET content = ?, created_date = ?, title = ?, added_by = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_POST,
        		post.getContent(),
        		post.getCreated_date(),
        		post.getTitle(),
        		post.getAdded_by(),
        		post.getId());
		
	}

	@Override
	public void deletePostById(int id) {
		
		 final String DELETE_POST= "DELETE FROM posts WHERE id = ?";
	        jdbc.update(DELETE_POST, id);
		
	}
   
}
