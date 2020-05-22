package http.authz

default allow = false

allow_user_roles {
	input.auth.principal.authorities[_].authority == "ROLE_USER"
}

allow {
	# To allow these paths as long as there are user roles
	allowedPaths := { [""], ["api", "v1", "kmo"] }  
	allow_user_roles == true
	allowedPaths[input.path]
}

# READ - SECTION1
allow { 
	allowedTeamRoles := { "Viewer", "Collaborator", "Owner"}

	input.method == "GET"
	input.path = ["api", "v1", "kmo", "p", _]
	allow_user_roles == true

	# some i
	team := "MAGMA" # input.auth.principal.teams[i]
	roleOfTeam := input.pacl[team][_]
	allowedTeamRoles[roleOfTeam]
}

# READ - SECTION2
allow {
	false
}

# READ - SECTION3
allow {
	false
}

# CREATE
allow {
	false
}

# UPDATE
allow {
	false
}

