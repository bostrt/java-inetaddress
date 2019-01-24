# What is?

A simple Java application that repeatedly runs `InetAddress.getByName` with a given hostname (`TEST_HOSTNAME` env var) and prints results. 

# How to run

## Avaiable environment variables
- `TEST_HOSTNAME` - The hostname to resolve. This is *required* and has no default value.
- `DEBUG` - Print out DNS query results each request. Default is `false` and only prints failures.
- `DELAY` - The delay in seconds between each DNS query. Default is `10` seconds.

## Without docker
```
# javac Main.java
DEBUG=true TEST_HOSTNAME=redhat.com java Main
Wed Jan 23 17:51:04 MST 2019
redhat.com => redhat.com/10.4.204.55
Time to resolve (ms) => 28
...
```

## With docker
```
# git clone https://github.com/bostrt/java-inetaddress.git
# cd java-inetaddress
# docker build -t java-inetaddress .
# docker run --rm -it -e TEST_HOSTNAME=redhat.com java-inetaddress

```

## With OpenShift
```
# oc new-app https://github.com/bostrt/java-inetaddress.git
# oc set env dc/java-inetaddress TEST_HOSTNAME=redhat.com DEBUG=false
```
