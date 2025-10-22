package com.loaraid.raidscheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="guild")
public class GuildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guild_id")
    private Long guildId;

    @OneToMany(mappedBy = "guild", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();

    @Column(name = "guild_name")
    private String guildName;
}
