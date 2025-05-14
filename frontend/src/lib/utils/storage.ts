export async function loadAndCacheResource<T>(key: string, fetcher: () => Promise<T>): Promise<T> {
    const cached = sessionStorage.getItem(key);
    if (cached) {
        return JSON.parse(cached) as T;
    }
    const data = await fetcher();
    sessionStorage.setItem(key, JSON.stringify(data));
    return data;
}