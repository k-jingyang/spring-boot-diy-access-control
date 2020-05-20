package http.authz

default allow = false

# READ - SECTION1
allow { 
	input.method == "GET"
	input.path == "/api/v1/kmo/p/"
}

# READ - SECTION2
allow {

}

# READ - SECTION3
allow {

}

# CREATE
allow {

}

# UPDATE
allow {

}

