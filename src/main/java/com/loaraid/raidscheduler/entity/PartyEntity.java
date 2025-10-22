package com.loaraid.raidscheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parties")
public class PartyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long partyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raid_id", nullable = false)
    private RaidEntity raid;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @OneToMany(mappedBy = "party",fetch = FetchType.LAZY)
    private List<PartyMembersEntity> party = new ArrayList<>();
}
