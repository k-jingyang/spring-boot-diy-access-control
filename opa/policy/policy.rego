package http.authz

default allow = false

roles = {
	"owner": {"permissions": []},
	"collaborator": {"super": "owner", "permissions": ["pokemon:location"]},
	"viewer": {"super": "collaborator", "permissions": ["pokemon:general-info"]},
}

roles_graph[role] = edges {
	roles[role]
	edges := {neighbor | roles[neighbor].super == role}
}

computed_permissions[role] = perms {
	roles[role]
	reachable := graph.reachable(roles_graph, {role})
	perms := {item | reachable[k]; item := roles[k].permissions[_]}
}

acl = {
	"1": {
		"ROCKET": "owner",
		"GALACTIC": "collaborator",
		"MAGMA": "viewer",
	},
	"2": {
		"ROCKET": "collaborator",
		"GALACTIC": "owner",
		"MAGMA": "viewer",
	},
}

has_user_role {
	input.auth.principal.authorities[_].authority == "ROLE_USER"
}

allow {
	# To allow these paths as long as there are user roles
	input.path = ""
	has_user_role
}

user_teams = input.auth.principal.teams

pokemon_id = input.pokemon_id

# READ - GENERAL INFORMATION
allow {
	input.method == "GET"

	input.path = ["api", "v1", "pokemon", _]
	has_user_role

	# "pokemon:location" is among the computed permissions for one of this user's user_teams
	computed_permissions[acl[pokemon_id][user_teams[_]]][_] == "pokemon:general-info"
}

# READ - LOCATION
allow {
	input.method == "GET"

	input.path = ["api", "v1", "pokemon", _, "location"]
	has_user_role

	# "pokemon:location" is among the computed permissions for one of this user's user_teams
	computed_permissions[acl[pokemon_id][user_teams[_]]][_] == "pokemon:location"
}
