FROM golang:alpine as build
WORKDIR /app
COPY ./fullcycle.go /app
RUN go build -ldflags '-s -w' fullcycle.go


FROM alpine:3.11.3
COPY --from=build /app .
ENTRYPOINT [ "./fullcycle" ]