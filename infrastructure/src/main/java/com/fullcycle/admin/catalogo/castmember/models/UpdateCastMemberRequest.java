package com.fullcycle.admin.catalogo.castmember.models;

import com.fullcycle.admin.catalogo.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}
