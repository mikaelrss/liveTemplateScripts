Random random = new Random()
String test = _1.toString().replaceAll(/.test./, '.')

String fileContent = new File(test).text
def result = (fileContent =~ /(Props = [ a-zA-Z0-9?:,(){}<>=\n]*)/)[0][1]
def props = result.toString().split(/Props = /)[1] + ";"

return props.replaceAll(/: string/, ": 'testString'")
        .replaceAll(/: number/, ": " + random.nextInt(100).toString())
        .replaceAll(/Array<number>/, "[" + random.nextInt(100) + "," + random.nextInt(100) + "]")
        .replaceAll(/Array<Array<[a-zA-Z]*>>/, "[[{}],[{}]]")
        .replaceAll(/Array<[a-zA-z<>]*>/, "[{}]")
        .replaceAll(/Array<[0-9\[\],]*>/, "[[" + random.nextInt(100) + "], [" + random.nextInt(100) + "]]")
        .replaceAll(/: Function/, ": jest.fn()")
        .replaceAll(/: \([a-zA-Z:<> ']*\) => Promise<[a-zA-Z)]*>/, ": jest.fn().mockImplementationOnce(() => Promise.resolve())")
        .replaceAll(/: \([a-zA-Z:<> ]*\) => [a-zA-Z<>]*/, ": jest.fn()")
