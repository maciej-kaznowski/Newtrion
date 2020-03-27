package com.innercirclesoftware

import javax.persistence.*

@Entity
@Table(name = "journeys")
data class Journey(@Id @GeneratedValue(strategy = GenerationType.AUTO) private val id: Long = 0)