function deco(target, key, descriptor) {
    console.log(target)
    console.log(key)
    console.log(descriptor)
}


class Sample {
    @deco
    printSome() {
        console.log("Print some")
    }
}
