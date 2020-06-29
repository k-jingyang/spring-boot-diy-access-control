package http.authz

test_graph {
	roles_graph.viewer == set()
	roles_graph.collaborator == {"viewer"}
	roles_graph.owner == {"collaborator"}
}

test_permissions {
	computed_permissions.owner == {"pokemon:general-info", "pokemon:location"}
	computed_permissions.collaborator == {"pokemon:general-info", "pokemon:location"}
	computed_permissions.viewer == {"pokemon:general-info"}
}
