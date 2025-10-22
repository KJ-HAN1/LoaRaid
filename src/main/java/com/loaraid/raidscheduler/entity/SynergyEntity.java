package com.loaraid.raidscheduler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "synergy")
public class SynergyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "synergy_id")
    private Long synergyId;

    @Column(name = "synergy_name")
    private String synergyName;

    @OneToMany(mappedBy = "synergy", fetch =FetchType.LAZY)
    private List<ClassSynergyMapEntity> classMaps = new ArrayList<>();
}
