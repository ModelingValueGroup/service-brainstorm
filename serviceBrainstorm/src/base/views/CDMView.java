//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2019 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
//                                                                                                                     ~
// Licensed under the GNU Lesser General Public License v3.0 (the 'License'). You may not use this file except in      ~
// compliance with the License. You may obtain a copy of the License at: https://choosealicense.com/licenses/lgpl-3.0  ~
// Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on ~
// an 'AS IS' BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the  ~
// specific language governing permissions and limitations under the License.                                          ~
//                                                                                                                     ~
// Maintainers:                                                                                                        ~
//     Wim Bast, Tom Brus, Ronald Krijgsheld                                                                           ~
// Contributors:                                                                                                       ~
//     Arjan Kok, Carel Bast                                                                                           ~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

package base.views;

import java.util.function.Function;

import org.modelingvalue.collections.Entry;
import org.modelingvalue.collections.List;
import org.modelingvalue.collections.Map;

import base.CDMObject;
import base.CDMProperty;

@SuppressWarnings({"unchecked", "SameParameterValue"})
public class CDMView {
    protected static Integer dispatchInteger(Map<String, Object> map, String field) {
        Object o = map.get(field);
        return o instanceof Number ? ((Number) o).intValue() : null;
    }

    protected static <T> T dispatchMap(Map<String, Object> map, String field, Function<Map<String, Object>, T> f) {
        Object o = map.get(field);
        return o instanceof Map ? f.apply((Map<String, Object>) o) : null;
    }

    protected static <T> List<T> dispatchList(Map<String, Object> map, String field, Function<Map<String, Object>, T> f) {
        Object o = map.get(field);
        if (o instanceof List) {
            return ((List<Map<String, Object>>) o).map(f).toList();
        }
        return List.of();
    }

    protected static <OWNER extends CDMObject> Map<String, Object> createWithId(OWNER owner) {
        return Map.of(Entry.of("id", owner.getId().toString()));
    }

    protected static <OWNER extends CDMObject, T extends CDMObject> Map<String, Object> addMap(OWNER owner, Map<String, Object> map, CDMProperty<OWNER, T> prop, Function<T, Object> f) {
        T obj = prop.get(owner);
        if (obj != null) {
            if (map == null) {
                map = Map.of();
            }
            map = map.put(prop.getName(), f.apply(obj));
        }
        return map;
    }

    protected static <OWNER extends CDMObject, T extends CDMObject> Map<String, Object> addList(OWNER owner, Map<String, Object> map, CDMProperty<OWNER, List<T>> prop, Function<T, Object> f) {
        List<T> obj = prop.get(owner);
        if (obj != null) {
            if (map == null) {
                map = Map.of();
            }
            map = map.put(prop.getName(), obj.map(f).toList());
        }
        return map;
    }

}
