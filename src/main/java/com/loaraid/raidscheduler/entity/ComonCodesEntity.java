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
@Table(name = "common_codes")
public class ComonCodesEntity {
    @Id
    @Column(name = "code_value")
    private String codeValue;

    @Column(name = "code_type")
    private String codeType;

    @Column(name = "code_name")
    private String codeName;

    @OneToMany(mappedBy = "statusCode",fetch = FetchType.LAZY)
    private List<RaidApplicationsEntity> raidStatuses = new ArrayList<>();

    @OneToMany(mappedBy = "roleCode",fetch = FetchType.LAZY)
    private List<UserEntity> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "classCode",fetch = FetchType.LAZY)
    private List<CharacterEntity> characters = new ArrayList<>();

    @OneToMany(mappedBy = "difficultyCode",fetch = FetchType.LAZY)
    private List<RaidEntity> raidDifficulty = new ArrayList<>();

    @OneToMany(mappedBy = "classCode", fetch = FetchType.LAZY)
    private List<ClassSynergyMapEntity> synergyMaps = new ArrayList<>();
}
