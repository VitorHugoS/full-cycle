FROM golang:alpine as build
WORKDIR /app
COPY ./fullcycle.go /app
RUN go build -ldflags '-s -w' fullcycle.go


FROM scratch
COPY --from=build /app .
ENTRYPOINT [ "./fullcycle" ]