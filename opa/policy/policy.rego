package http.authz

default allow = false

allow_user_roles {
	input.auth.principal.authorities[_].authority == "ROLE_USER"
}

allow {
	# To allow these paths as long as there are user roles
    input.path = ""
	allow_user_roles == true
}

# READ - GENERAL INFORMATION
allow {
	allowedTeamRoles := { "Viewer", "Collaborator", "Owner"}

	input.method == "GET"
    input.path = ["api", "v1", "pokemon", _]
	allow_user_roles == true

	# some i
	team := input.auth.principal.teams[i]
	roleOfTeam := input.pacl[team][_]
	allowedTeamRoles[roleOfTeam]
}


# READ - LOCATION
allow { 
	allowedTeamRoles := { "Collaborator", "Owner" }

	input.method == "GET"
	input.path = ["api", "v1", "pokemon", _, "location"]
	allow_user_roles == true

	# some i
	team := input.auth.principal.teams[i]
    roleOfTeam := input.pacl[team][_]
	allowedTeamRoles[roleOfTeam]
}


