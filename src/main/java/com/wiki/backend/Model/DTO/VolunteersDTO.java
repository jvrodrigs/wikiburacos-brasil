package com.wiki.backend.Model.DTO;

import com.wiki.backend.Model.PostShare;
import com.wiki.backend.Model.Volunteers;

public class VolunteersDTO {
    private Volunteers post;
    private PostShare share;

    public Volunteers getPost() {
        return post;
    }

    public void setPost(Volunteers post) {
        this.post = post;
    }

    public PostShare getShare() {
        return share;
    }

    public void setShare(PostShare share) {
        this.share = share;
    }
}
