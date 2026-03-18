package com.example.movie_service.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="episodes")
public class Episode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "slug")
    private String slug;

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(name= "link_embed")
    private String linkEmbed;

    public String getLinkEmbed() {
        return this.linkEmbed;
    }

    public void setLinkEmbed(String linkEmbed) {
        this.linkEmbed = linkEmbed;
    }

    @Column(name = "link_m3u8")
    private String linkM3u8;

    public String getLinkM3u8() {
        return this.linkM3u8;
    }

    public void setLinkM3u8(String linkM3u8) {
        this.linkM3u8 = linkM3u8;
    }

    @ManyToOne
    @JoinColumn(name = "server_id")
    private EpisodeServer episodeServer;

    public EpisodeServer getEpisodeServer() {
        return this.episodeServer;
    }

    public void setEpisodeServer(EpisodeServer episodeServer) {
        this.episodeServer = episodeServer;
    }

    public Episode(){

    }

}
