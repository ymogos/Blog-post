package com.yohannes.blogpost.models;

import java.sql.Date;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Yohannes
 */
public class Post {
    
    private int id;
    @NotBlank(message = "Content must noy be Empity.")
    private String content;
    private Date created_date;
	@NotBlank(message = "Name must not be empty.")
	private String title;
	@NotBlank(message = "Added by must not be empity.")
	private String added_by;
	
	
    
    public String getAdded_by() {
		return added_by;
	}

	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", content=" + content +",created_date=" + created_date +",added_by=" + added_by +'}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.content);
        hash = 79 * hash + Objects.hashCode(this.created_date);
        hash = 79 * hash + Objects.hashCode(this.added_by);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.id != other.id) {
            return false;
        }
       
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.created_date, other.created_date)) {
            return false;
        }
        
        if (!Objects.equals(this.added_by, other.added_by)) {
            return false;
        }
        
        return true;
    }

    
}
