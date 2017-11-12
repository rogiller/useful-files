package com.flex.common

import com.flex.warehouse.prompt.ScanPromptType
import spock.lang.Ignore
import spock.lang.Specification

//If you want to run the test, just comment out @Ignore below
@Ignore
class InMemoryIntegrationTest extends Specification {

    def "primer"() {

        //This test is just non-test to take the initial hit of running tests

        expect:

            long start2 = System.currentTimeMillis()

            (1..10_000_000).each {

            }

            ScanPromptType.getInstance('sn-prompt')

            println(System.currentTimeMillis() - start2 + " (ms) primer")

    }


    def "Collections.emptyList() list"() {

        expect:

            long start2 = System.currentTimeMillis()

            def results = []

            (1..10_000_000).each {
                results.add(emptyList())
            }

            println(System.currentTimeMillis() - start2 + " (ms) Collections.emptyList() | Size: " + results.size())

    }

    def "new ArrayList list"() {

        expect:

            long start = System.currentTimeMillis()

            def results = []

            (1..10_000_000).each {
                results.add(blankArrayList())
            }

            println(System.currentTimeMillis() - start + " (ms) new ArrayList list | Size: " + results.size())

    }

    def "enum by map"() {

        expect:

            long start = System.currentTimeMillis()

            (1..10_000_000).each {
                ScanPromptType.getInstance('sn-prompt')
            }

            println(System.currentTimeMillis() - start + " (ms) enum by map")

    }

    def "enum by values"() {

        //This test is the only one here that really wins the in memory optimize test. Approx factor of 10 slower than "enum by map"

        expect:

            long start = System.currentTimeMillis()

            (1..10_000_000).each {
                getInstanceByValues('sn-prompt')
            }

            println(System.currentTimeMillis() - start + " (ms) enum by values")

    }

    List<String> blankArrayList(){
        return []
    }

    List<String> emptyList(){
        return Collections.emptyList()
    }

    ScanPromptType getInstanceByValues(String code) {
        ScanPromptType type = null
        ScanPromptType.values().each {
            if(it.code == code){
                type = it
            }
        }
        return type
    }

}
