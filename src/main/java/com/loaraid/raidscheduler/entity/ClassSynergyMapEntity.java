package com.loaraid.raidscheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "class_synergy_map")
public class ClassSynergyMapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_synergy_id")
    private Long classSynergyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_code", nullable = false)
    private ComonCodesEntity classCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "synergy_id", nullable = false)
    private SynergyEntity synergy;
}
