## To run project

**Start OPA**

```
$ cd opa
$ docker-compose up
```

**Start Spring project**

```
$ ./gradlew bootRun
```

**Push acl rules to OPA**

```
curl -vL -X PUT 'http://localhost:8181/v1/data/acl' -H "Content-Type: application/json" --data
'@opa/acl.json' | jq
```

**Try it out**

Point your browser to

```
http://localhost:8080/api/v1/pokemon/1/location
```

Log in with `magma_grunt` and `password`. You should see that this path is Forbidden.

Now update the rules, change `"MAGMA": "viewer"` to `"MAGMA": "collaborator"`,
and refresh. Now the page should load correctly.

## Available users

| User                   | Password |
| ---                    | ---      |
| `rocket_grunt`         | password |
| `magma_grunt`          | password |
| `magma_galactic_grunt` | password |
| `galactic_grunt`       | password |

## URLs to play with
1. http://localhost:8080/api/v1/pokemon/1/
1. http://localhost:8080/api/v1/pokemon/1/location

## Installing docker-compose in the offline world

https://stackoverflow.com/questions/53578634/how-to-install-docker-compose-offline
